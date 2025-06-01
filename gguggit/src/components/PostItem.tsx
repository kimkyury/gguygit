import React from 'react';
import './PostItem.css'

import type { Post } from '@utils/types/Post';

export const PostItem = ({ post, align }: { post: Post, align: 'left' | 'right' }) => {
    const isLeft = align === 'left';

    return (
        <div className={`post-item-container ${isLeft ? 'left' : 'right'}`}>
            <img
                src={post.albumImage}
                alt="Album"
                className="post-item-image"
            />
            <div className="post-item-content">
                <div className="post-item-author">작성자: {post.author}</div>
                <div className="post-item-timestamp">{post.timestamp}</div>
                <div className="post-item-text">{post.text}</div>
                <div className="post-item-link">
                    <a href={post.videoUrl} target="_blank" rel="noopener noreferrer">
                        🎵 Watch on YouTube
                    </a>
                </div>
            </div>
        </div>
    );
};
