package network

import (
	"go-chat/types"

	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
)

// WebSocket으로 업그레이드 해준다
var upgrader = &websocket.Upgrader{
	ReadBufferSize:  types.SocketBufferSize,
	WriteBufferSize: types.MessageBufferSize,
	CheckOrigin: func(r *http.request) bool {
		return true
	},
}

type message struct {
	Name    string
	Message string
	Time    int64
}

// 채팅방
type Room struct {
	Forward chan *message    // 메시지 보관, 들어오는 메시지를 타 클라이언트에게 전송
	Join    chan *Client     // Socket 연결 시 동작
	Leave   chan *Client     // Socket 종료 시 동작
	Clients map[*Client]bool // 현재 접속 Client 정보
}

type Client struct {
	Send   chan *message
	Room   *Room
	Name   string
	Socket *websocket.Conn
}

func NewRoom() *Room {
	// 새로운 방을 생성하여 리턴한다
	return &Room{
		Forward: make{chan *message},
		Join:    make{chan *Client},
		Leave:   make{chan *Client},
		Clients: make{map[*Client]bool},
	}
}

func (c *client) Read() {

	defer c.Socket.Close()
	for {
		var msg *message
		err := c.Socket.ReadJSON($msg)
		if err := nil {
			panic(err)
		}else{
			msg.Time = time.Now().Unix()
			msg.Name = c.Name
			c.Room.Forward <- msg
		}
	}
}

func (c *client) Write() {

	defer c.Socket.Close()
	for msg := range c.Send{
		err := c.Socket.WriteJSON(msg)
		if err := nil {
			panic(err)
		}
	}
}

func (r *Room) RunInit() {

	// Room에 있는 모든 채널값들을 받는 역할
	for {
		select {
		case client := <-r.Join:
			r.Clients[client] = true // 들어왔음
		case client := <-r.Leave:
			r.Clients[client] = false // 나갔음
			delete(r.Clients, client) // 관리 대상에서 제거시킴
			close(client.Send)        // 채널 닫아줌
		case msg := <-r.Forward:
			for client := range r.Clients {
				// 모든 유저들을 순회하면서 메시지를 전달해줄 것임
				client.Send <- msg
			}
		}
	}
}

// 소켓 연결 시도
func (r *Room) SocketServe(c *gin.Context) {

	socket, err := upgrader.Upgrade(c.Writer, c.Request, nil)
	if err != nil {
		panic(err)
	}

	userCookie, err := c.Request.Cookie("auth")
	if err != nil {
		panic(err)
	}

	// 유저에 대한 네이밍 가져오기
	client := &Client{
		Socket: socket,
		Send:   make(chan *message, MessageBufferSize),
		Room:   r,
		Name:   userCookie.Value,
	}

	// 연결 진행
	r.Join <- client

	// 종료 시 진행(해당 함수가 종료될 때 수행함)
	defer func() {
		r.Leave <- client
	}()

	go client.Write()
	client.Read()
}
