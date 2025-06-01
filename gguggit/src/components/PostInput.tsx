import React, { useState } from 'react';
import type { Post } from '@utils/types/Post';

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
        <div style={{ margin: '20px 0', backgroundColor: '#fff', padding: '10px', borderRadius: '8px' }}>
            <textarea
                value={text}
                onChange={e => setText(e.target.value)}
                maxLength={500}
                placeholder="MAX-length: 500"
                style={{ width: '100%', height: '80px', padding: '10px' }}
            />
            <button onClick={handleSubmit} style={{ marginTop: '10px' }}>
                등록하기
            </button>
        </div>
    );
};
