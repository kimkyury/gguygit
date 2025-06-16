import React from 'react';

import type { MusicPost } from '@utils/types/musicPost'
import { PostItem } from '@components/PostItem';

export const PostList = (
    {
        posts, onUpdatePost
    }: {
        posts: MusicPost[];
        onUpdatePost: (updated: MusicPost) => void;
    }) => {

    console.log(posts.map((post, index) => `index ${index}: post.id = ${post.id}`));


    return (
        <div style={{ marginTop: '60px' }}>
            {posts.map((post, index) => (
                <PostItem onUpdatePost={onUpdatePost} key={post.id} post={post} align={index % 2 === 0 ? 'left' : 'right'} />
            ))}
        </div>
    );
};
