import React, { useState, useEffect } from 'react';
import type { Post } from '@utils/types/Post';
import './PostInput.css'

export const PostInput = ({ onAddPost }: { onAddPost: (post: Post) => void }) => {
    const [text, setText] = useState('');
    const [previewThumbnail, setPreviewThumbnail] = useState<string>('');
    const [videoUrl, setVideoUrl] = useState<string>('');


    /* 2025.06.01 Youtube Video에서 Thumbnail 추출하기 */
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

    /* ctrl+v 가 일어나는 시점에만 Youtube Link 검사하기 */
    const handlePaste = (e: React.ClipboardEvent<HTMLTextAreaElement>) => {
        const pasteData = e.clipboardData.getData('text');

        const { videoUrl, thumbnail } = extractYoutubeData(pasteData);
        setPreviewThumbnail(thumbnail);
        setVideoUrl(videoUrl);
    };

    const handleSubmit = () => {
        if (text.trim() === '') return;
        const { videoUrl, thumbnail } = extractYoutubeData(text);
        const textWithoutUrl = text.replace(videoUrl, '').trim(); // Youtube Link 내용은 삭제

        const newPost: Post = {
            id: Date.now(),
            text: textWithoutUrl,
            videoUrl,
            albumImage: thumbnail || 'https://via.placeholder.com/100',
            author: 'Anonymous',
            timestamp: new Date().toLocaleString(),
        };

        onAddPost(newPost);

        // 초기화
        setText('');
        setPreviewThumbnail('');
        setVideoUrl('');
    };

    /* ctrl + Enter = 바로 등록 */
    const handleKeyDown = (e: React.KeyboardEvent<HTMLTextAreaElement>) => {
        if (e.ctrlKey && e.key === 'Enter') {
            handleSubmit();
        }
    };

    return (
        <div className="post-input-container">
            <textarea
                value={text}
                onChange={e => setText(e.target.value)}
                maxLength={500}
                placeholder="maxlength: 500"
                className="post-input-textarea"
                onKeyDown={handleKeyDown}
                onPaste={handlePaste}
            />


            {/* 🚀 썸네일 미리보기 */}
            {previewThumbnail && (
                <div className="post-input-preview">
                    <img src={previewThumbnail} alt="YouTube Thumbnail" />
                    <div>
                        <a href={videoUrl} target="_blank" rel="noopener noreferrer">
                            🎵 Go to YouTube
                        </a>
                    </div>
                </div>
            )}




            <div className="post-input-actions">
                <button onClick={handleSubmit} className="post-input-button">
                    +
                </button>
            </div>
        </div>
    );
};
