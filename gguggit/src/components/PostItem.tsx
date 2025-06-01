import React from 'react';

import type { Post } from '@utils/types/Post';

export const PostItem = ({ post, align }: { post: Post, align: 'left' | 'right' }) => {
    const isLeft = align === 'left';

    return (
        <div
            style={{
                display: 'flex',
                flexDirection: isLeft ? 'row' : 'row-reverse',
                alignItems: 'flex-start',
                marginBottom: '15px',
            }}
        >
            <img
                src={post.albumImage}
                alt="Album"
                style={{ width: '80px', height: '80px', borderRadius: '8px', margin: '0 10px' }}
            />
            <div
                style={{
                    backgroundColor: '#fff',
                    padding: '10px',
                    borderRadius: '8px',
                    maxWidth: '60%',
                    textAlign: 'left',
                }}
            >
                <div style={{ fontSize: '14px', color: '#555' }}>작성자: {post.author}</div>
                <div style={{ marginTop: '5px' }}>{post.text}</div>
            </div>
        </div>
    );
};
