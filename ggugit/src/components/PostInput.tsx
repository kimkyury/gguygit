import React, { useState, useEffect } from 'react';
import type { Post } from '@typings/Post';
import './PostInput.css';

export const PostInput = ({ onAddPost }: { onAddPost: (post: Post) => void }) => {
    const [text, setText] = useState('');
    const [previewThumbnail, setPreviewThumbnail] = useState<string>('');
    const [videoUrl, setVideoUrl] = useState<string>('');
    const [rating, setRating] = useState<number>(0);

    const extractYoutubeData = (text: string): { videoUrl: string; thumbnail: string } => {
        const urlRegex = /(https?:\/\/(?:www\.)?youtu(?:be\.com\/watch\?v=|\.be\/)([\w\-]+))/;
        const match = text.match(urlRegex);

        if (match) {
            const videoUrl = match[1];
            const videoId = match[2];
            const thumbnail = `https://img.youtube.com/vi/${videoId}/hqdefault.jpg`;

            return { videoUrl, thumbnail };
        }

        return { videoUrl: '', thumbnail: '' };
    };

    useEffect(() => {
        const { videoUrl, thumbnail } = extractYoutubeData(text);
        setPreviewThumbnail(thumbnail);
        setVideoUrl(videoUrl);
    }, [text]);

    const handleSubmit = () => {
        if (text.trim() === '') return;

        const textWithoutUrl = text.replace(videoUrl, '').trim();

        const newPost: Post = {
            id: Date.now(),
            text: textWithoutUrl,
            videoUrl,
            albumImage: previewThumbnail || 'https://via.placeholder.com/100',
            author: 'Anonymous',
            timestamp: new Date().toLocaleString(),
            rating, // ⭐ 저장
        };

        onAddPost(newPost);

        setText('');
        setPreviewThumbnail('');
        setVideoUrl('');
        setRating(0);
    };

    const handleKeyDown = (e: React.KeyboardEvent<HTMLTextAreaElement>) => {
        if (e.ctrlKey && e.key === 'Enter') {
            handleSubmit();
        }
    };

    return (
        <div className="post-input-container">
            <div className="post-input-main">
                <textarea
                    value={text}
                    onChange={e => setText(e.target.value)}
                    maxLength={500}
                    placeholder="max-length: 500"
                    className="post-input-textarea"
                    onKeyDown={handleKeyDown}
                />

                {previewThumbnail && (
                    <div className="post-input-thumbnail">
                        <img src={previewThumbnail} alt="YouTube Thumbnail" />
                    </div>
                )}
            </div>

            {/* ⭐ 별점 */}
            <div className="post-input-rating">
                {[1, 2, 3, 4, 5].map((star) => (
                    <span
                        key={star}
                        className={`star ${star <= rating ? 'filled' : ''}`}
                        onClick={() => setRating(star)}
                    >
                        ★
                    </span>
                ))}
            </div>

            <div className="post-input-actions">
                <button onClick={handleSubmit} className="post-input-button">
                    Submit
                </button>
            </div>
        </div>
    );
};
