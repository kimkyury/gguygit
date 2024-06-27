import React, { useState, useEffect, useCallback } from 'react';
import axios from './axios';
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import './App.css';

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
    try {
      const response = await axios.get('/rooms');
      setRooms(response.data);
    } catch (error) {
      console.error('Error fetching rooms:', error);
    }
  };

  const createRoom = async (roomName) => {
    try {
      await axios.post('/rooms', null, { params: { chatroomName: roomName } });
      fetchRooms();
    } catch (error) {
      console.error('Error creating room:', error);
    }
  };

  const joinRoom = useCallback((room) => {
    setCurrentRoom(room);
    const socket = new SockJS('http://localhost:8080/ws', null, { transports: ['websocket'] });
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      stompClient.subscribe(`/topic/${room.id}`, (message) => {
        const newMessage = JSON.parse(message.body);
        setMessages((prevMessages) => [...prevMessages, newMessage]);
      });
    });
    setClient(stompClient);
  }, []);

  const sendMessage = () => {
    if (client && currentRoom && newMessage.trim() !== '') {
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

  const handleKeyUp = (e) => {
    if (e.key === 'Enter' && newMessage.trim() !== '') {
      sendMessage();
    }
  };

  return (
    <div className="app">
      <h1>Chat Application</h1>
      <div className="input-group">
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="input"
        />
        <input
          type="text"
          placeholder="Create Room"
          onKeyDown={(e) => e.key === 'Enter' && createRoom(e.target.value)}
          className="input"
        />
      </div>
      <div className="rooms">
        <h2>Rooms</h2>
        <ul className="room-list">
          {rooms.map((room) => (
            <li key={room.id} onClick={() => joinRoom(room)} className="room-item">
              {room.name}
            </li>
          ))}
        </ul>
      </div>
      {currentRoom && (
        <div className="chat-room">
          <h2>Room: {currentRoom.name}</h2>
          <div className="messages">
            <ul>
              {messages.map((message, index) => (
                <li key={index} className="message-item">{`${message.sender}: ${message.content}`}</li>
              ))}
            </ul>
            <input
              type="text"
              value={newMessage}
              onChange={(e) => setNewMessage(e.target.value)}
              onKeyUp={handleKeyUp}
              className="input"
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default App;
