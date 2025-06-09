import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import coverImg from '@assets/gyu1_bg.png'
import { PostInput } from '@components/PostInput';
import { PostList } from '@components/PostList';
import type { MusicPost } from '@utils/types/musicPost';
import { getAllMusicPosts, createMusicPost } from '@features/music/musicPostApi.ts';

import './Music.css'
// function 선언문 : 디버깅 시 컴포넌트 이름이 명확히 찍힌다
// 불러올 때는 {}를 쓰지 않고 부른다 
// React4 공식 스타일 가이드에서 "페이지 단위 컴포넌트"는 보통 export default function을 쓴다
export default function Music() {
    const [posts, setPosts] = useState<MusicPost[]>([]);

    const handleAddPost = async (newPost: MusicPost) => {
        await createMusicPost({
            text: newPost.text,
            videoUrl: newPost.videoUrl,
            imageUrl: newPost.imageUrl,
            author: newPost.author,
            rating: newPost.rating,
        });

        setPosts(prev => [newPost, ...prev]);
    }

    useEffect(() => {
        getAllMusicPosts()
            .then(res => {
                const loaded = res.map((post: MusicPost) => ({
                    id: post.id,
                    text: post.text,
                    videoUrl: post.videoUrl,
                    imageUrl: post.imageUrl,
                    author: post.author,
                    // timestamp: new Date(post.createdDate).toLocaleString(),
                    rating: post.rating,
                    createdDate: post.createdDate,
                    updatedDate: post.updatedDate
                }));
                setPosts(loaded);
            })
            .catch(e => console.error('failed to load posts', e));
    }, []);

    return (
        <div className="flex-100-100">
            {/* <motion.div style={{ display: 'flex', width: '100vw', height: '100vh' }}> */}
            <motion.div className='left-panel'>
                {/* 좌상 */}
                <motion.img
                    style={{ width: 120, height: 'auto' }}
                    src={coverImg}
                    initial={{ opacity: 0 }}
                    animate={{ opacity: 1 }}
                    transition={{ duration: 1 }}
                />

                <div className="text-columns">
                    {/* 좌하 */}
                    <motion.h1
                        className="text-title"
                        initial={{ opacity: 0, y: 50 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 1 }}>
                        Gyu's
                    </motion.h1>
                    <motion.h1
                        className="text-title text-title-offset-right"
                        initial={{ opacity: 0, y: 50 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 1 }}>
                        Musics
                    </motion.h1>
                </div>
            </motion.div>

            {/* 우 */}
            <motion.div
                className='right-panel'
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ duration: 0.5, delay: 0.4 }}
            >
                <div
                    style={{ margin: 40 }} >
                    <PostInput onAddPost={handleAddPost} />
                    <PostList posts={posts} />
                </div>
            </motion.div >

            {/* </motion.div > */}
        </div >
    );
};
