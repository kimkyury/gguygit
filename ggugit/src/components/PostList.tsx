import React from 'react';

import type { Post } from '@utils/types/Post';
import { PostItem } from '@components/PostItem';

export const PostList = ({ posts }: { posts: Post[] }) => {
    return (
        <div style={{ marginTop: '60px' }}>
            {posts.map((post, index) => (
                <PostItem key={post.id} post={post} align={index % 2 === 0 ? 'left' : 'right'} />
            ))}
        </div>
    );
};
