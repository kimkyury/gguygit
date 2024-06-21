import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import './ChatRoom.css';

const ChatRoom = () => {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');
  const socket = new SockJS('http://localhost:8080/ws');
  const stompClient = Stomp.over(socket);

  useEffect(() => {
    stompClient.connect({}, frame => {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/topic/public', message => {
        showMessage(JSON.parse(message.body));
      });
    });
    return () => {
      if (stompClient) {
        stompClient.disconnect();
      }
    };
  }, []);

  const showMessage = (message) => {
    setMessages(prevMessages => [...prevMessages, message]);
  };

  const sendMessage = () => {
    if (stompClient && input) {
      const messageContent = {
        content: input,
        sender: 'User', // Modify as needed
        timestamp: new Date().toISOString()
      };
      stompClient.send("/app/chat.send", {}, JSON.stringify(messageContent));
      setInput('');
    }
  };

  return (
    <div className="chat-room">
      <div className="chat-box">
        {messages.map((msg, index) => (
          <div key={index}>
            <b>{msg.sender}</b>: {msg.content} <i>({msg.timestamp})</i>
          </div>
        ))}
      </div>
      <div className="input-box">
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Enter your message..."
        />
        <button onClick={sendMessage}>Send</button>
      </div>
    </div>
  );
};

export default ChatRoom;
