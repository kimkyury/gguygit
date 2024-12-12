package network

import (
	"log"

	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
)

type Network struct {
	engin *gin.Engine
}

// Network가 사용할 수 있는 메소드다
func NewServer() *Network {
	n := Network{engin: gin.New()}

	n.engin.Use(gin.Logger())   // 로그를 찍어라
	n.engin.Use(gin.Recovery()) // 서버 장애 시 자동으로 failover?
	n.engin.Use(cors.New(cors.Config{
		AllowWebSockets:  true,
		AllowOrigins:     []string{"*"},
		AllowMethods:     []string{"GET", "POST", "PUT"},
		AllowHeaders:     []string{"*"},
		AllowCredentials: true,
	}))

	r := NewRoom()
	go r.RunInit() // 백그라운드 동작을 의미함

	n.engin.GET("/room", r.SocketServe)

	return &n
}

// Nework 가 사용할 수 있는 메소드다
func (n *Network) StartServer() error {
	log.Println("Starting Server")
	return n.engin.Run(":8080")
}
