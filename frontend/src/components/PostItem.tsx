import React from 'react';
import type { Post } from '@typings/Post';
import './PostItem.css';

export const PostItem = ({ post, align }: { post: Post; align: 'left' | 'right' }) => {
    const isLeft = align === 'left';

    return (
        <div className={`post-item-container ${isLeft ? 'left' : 'right'}`}>
            <img
                src={post.imageUrl}
                alt="Album"
                className="post-item-image"
            />
            <div className="post-item-content ">

                <div className="flex">
                    <div className="post-item-author">작성자: {post.author}</div>
                    <div className="post-item-timestamp">
                        {post.createdDate}
                    </div>
                    {/* ⭐ 별점 표시 */}
                    <div className="post-item-rating"
                        style={{ display: 'flex', justifyContent: 'flex-end', flex: 1 }}
                    >
                        {'★'.repeat(post.rating)}
                        {'☆'.repeat(5 - post.rating)}
                    </div>
                </div>
                <div className="post-item-text">{post.text}</div>


                {/* {post.videoUrl && (
                    <div className="post-item-link">
                        <a href={post.videoUrl} target="_blank" rel="noopener noreferrer">
                            🎵 Go to YouTube
                        </a>
                    </div>
                )} */}
            </div>
        </div >
    );
};
