import React, { useState } from 'react';
import type { Post } from '@utils/types/Post';
import './PostInput.css'

export const PostInput = ({ onAddPost }: { onAddPost: (post: Post) => void }) => {
    const [text, setText] = useState('');

    const handleSubmit = () => {
        if (text.trim() === '') return;

        const newPost = {
            id: Date.now(),
            text,
            albumImage: 'https://via.placeholder.com/100', // 예시용, 나중에 실제 음악 정보로 교체
            author: '작성자',
        };

        onAddPost(newPost);
        setText('');
    };

    return (
        <div className="post-input-container">
            <textarea
                value={text}
                onChange={e => setText(e.target.value)}
                maxLength={500}
                placeholder="max-length: 500"
                className="post-input-textarea"
            />
            <button onClick={handleSubmit} className="post-input-button">
                +
            </button>
        </div>
    );
};
