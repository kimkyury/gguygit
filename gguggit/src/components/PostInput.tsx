import React, { useState } from 'react';
import type { Post } from '@utils/types/Post';
import './PostInput.css'

export const PostInput = ({ onAddPost }: { onAddPost: (post: Post) => void }) => {
    const [text, setText] = useState('');
    const [videoUrl, setVideoUrl] = useState('');

    /* 2025.06.01 Youtube Video에서 Thumbnail 추출하기 */
    const extractYoutubeThumbnail = (url: string): string => {
        try {
            const urlObj = new URL(url);
            const videoId = urlObj.searchParams.get('v');

            return videoId
                ? `https://img.youtube.com/vi/${videoId}/hqdefault.jpg`
                : 'https://via.placeholder.com/100'; // fallback
        } catch (error) {
            console.error('Invalid URL:', url);
            console.error(error);
            return 'https://via.placeholder.com/100';
        }
    };

    const handleSubmit = () => {
        if (text.trim() === '') return;

        const thumbnail = extractYoutubeThumbnail(videoUrl);

        const newPost: Post = {
            id: Date.now(),
            text,
            videoUrl,
            albumImage: thumbnail,
            author: 'Kkr',
            timestamp: new Date().toLocaleString(),
        };

        onAddPost(newPost);
        setText('');
        setVideoUrl('');
    };

    return (
        <div className="post-input-container">
            <textarea
                value={text}
                onChange={e => setText(e.target.value)}
                maxLength={500}
                placeholder="Write your music reflection here..."
                className="post-input-textarea"
            />
            <input
                type="text"
                value={videoUrl}
                onChange={e => setVideoUrl(e.target.value)}
                placeholder="Paste YouTube link here"
                className="post-input-url"
            />
            <button onClick={handleSubmit} className="post-input-button">
                +
            </button>
        </div>
    );
};
