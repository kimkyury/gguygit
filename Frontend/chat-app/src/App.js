import React, { useState, useEffect } from 'react';
import axios from './axios';
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const App = () => {
  const [rooms, setRooms] = useState([]);
  const [currentRoom, setCurrentRoom] = useState(null);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const [client, setClient] = useState(null);
  const [username, setUsername] = useState('');

  useEffect(() => {
    fetchRooms();
  }, []);

  const fetchRooms = async () => {
    const response = await axios.get('/rooms');
    setRooms(response.data);
  };

  const createRoom = async (roomName) => {
    await axios.post('/rooms', null, { params: { chatroomName: roomName } });
    fetchRooms();
  };

  const joinRoom = (room) => {
    setCurrentRoom(room);
    const socket = new SockJS('http://localhost:8080/ws');
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      stompClient.subscribe(`/topic/${room.id}`, (message) => {
        const newMessage = JSON.parse(message.body);
        setMessages((prevMessages) => [...prevMessages, newMessage]);
      });
    });
    setClient(stompClient);
  };

  const sendMessage = () => {
    if (client && currentRoom) {
      const chatMessage = {
        sender: username,
        content: newMessage,
        chatRoomId: currentRoom.id,
        type: 'CHAT'
      };
      client.send('/app/chat/message', {}, JSON.stringify(chatMessage));
      setNewMessage('');
    }
  };

  return (
    <div>
      <h1>Chat Application</h1>
      <div>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="text"
          placeholder="Create Room"
          onKeyDown={(e) => e.key === 'Enter' && createRoom(e.target.value)}
        />
      </div>
      <div>
        <h2>Rooms</h2>
        <ul>
          {rooms.map((room) => (
            <li key={room.id} onClick={() => joinRoom(room)}>
              {room.name}
            </li>
          ))}
        </ul>
      </div>
      {currentRoom && (
        <div>
          <h2>Room: {currentRoom.name}</h2>
          <div>
            <ul>
              {messages.map((message, index) => (
                <li key={index}>{`${message.sender}: ${message.content}`}</li>
              ))}
            </ul>
            <input
              type="text"
              value={newMessage}
              onChange={(e) => setNewMessage(e.target.value)}
              onKeyDown={(e) => e.key === 'Enter' && sendMessage()}
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default App;
