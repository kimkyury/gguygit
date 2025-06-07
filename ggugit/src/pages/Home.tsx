import React, { useState } from 'react';
import { PostInput } from '@components/PostInput';
import { PostList } from '@components/PostList';
import type { Post } from '@utils/types/Post';
import './Home.css';

export const Home = () => {
    const [posts, setPosts] = useState<Post[]>([]);

    const handleAddPost = (newPost: Post) => {
        setPosts(prev => [newPost, ...prev]);
    };

    return (
        <div className="home-container">
            <h1 className="home-title">Kgyury's 🎵</h1>
            <PostInput onAddPost={handleAddPost} />
            <PostList posts={posts} />
        </div>
    );
}