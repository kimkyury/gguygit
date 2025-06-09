import React, { useState, useEffect } from 'react';
import type { MusicPost } from '@utils/types/musicPost';
import './PostInput.css';

export const PostInput = ({ onAddPost }: { onAddPost: (post: MusicPost) => void }) => {
    const [text, setText] = useState('');
    const [previewThumbnail, setPreviewThumbnail] = useState<string>('');
    const [videoUrl, setVideoUrl] = useState<string>('');
    const [rating, setRating] = useState<number>(0);

    useEffect(() => {
        const { videoUrl, thumbnail } = extractYoutubeData(text);
        setPreviewThumbnail(thumbnail);
        setVideoUrl(videoUrl);
    }, [text]);

    /**
     * Extract Youtube VideoUrl And thumbnail
     * @param text 
     * @returns {videoUrl, thumbnail}
     */
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

    /**
     * Submit Inputbox
     * @returns 
     */
    const handleSubmit = () => {
        if (text.trim() === '') return;

        const textWithoutUrl = text.replace(videoUrl, '').trim();

        const newPost: MusicPost = {
            id: Date.now(),
            text: textWithoutUrl,
            videoUrl,
            imageUrl: previewThumbnail || 'https://via.placeholder.com/100',
            author: 'Anonymous',
            createdDate: new Date().toLocaleString(),
            updatedDate: new Date().toLocaleString(),
            rating, // ⭐ 저장
        };

        onAddPost(newPost);

        // init Input info
        setText('');
        setPreviewThumbnail('');
        setVideoUrl('');
        setRating(0);
    };

    /**
     * when press [Enter], Submit InputBox
     * @param e keyEvent
     */
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
            <div
                style={{ display: "flex", justifyContent: "flex-end" }}>
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
        </div>
    );
};
