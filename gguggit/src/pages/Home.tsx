import React, { useState } from 'react';
import { PostInput } from '@components/PostInput';
import { PostList } from '@components/PostList';

export const Home = () => {
    const [posts, setPosts] = useState<any[]>([]);

    const handleAddPost = (newPost: any) => {
        setPosts(prev => [newPost, ...prev]);
    };

    return (
        <div style={{ padding: '20px', backgroundColor: '#f4f4f4', minHeight: '100vh' }}>
            <h1>🎵</h1>
            <PostInput onAddPost={handleAddPost} />
            <PostList posts={posts} />
        </div>
    );
};
