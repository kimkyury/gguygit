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
// };
// return (
//     <motion.div
//         style={{
//             display: 'flex',
//             width: '100vw',
//             height: '100vh'
//         }}
//     >
//         {/* 좌측 영역 (1번) */}
//         <motion.div
//             style={{
//                 flex: 2,
//                 display: 'flex',
//                 flexDirection: 'column'
//             }}
//         >
//             {/* 좌상 영역 (1-1번) */}
//             <motion.div
//                 style={{
//                     flex: 2,
//                     backgroundColor: '#ffadad' // 연핑크
//                 }}
//                 initial={{ opacity: 0 }}
//                 animate={{ opacity: 1 }}
//                 transition={{ duration: 0.5 }}
//             />

//             {/* 좌하 영역 (1-2번) */}
//             <motion.div
//                 style={{
//                     flex: 8,
//                     backgroundColor: '#ffd6a5' // 연주황
//                 }}
//                 initial={{ opacity: 0 }}
//                 animate={{ opacity: 1 }}
//                 transition={{ duration: 0.5, delay: 0.2 }}
//             />
//         </motion.div>

//         {/* 우측 영역 (2번) */}
//         <motion.div
//             style={{
//                 flex: 8,
//                 backgroundColor: '#caffbf' // 연초록
//             }}
//             initial={{ opacity: 0 }}
//             animate={{ opacity: 1 }}
//             transition={{ duration: 0.5, delay: 0.4 }}
//         />
//     </motion.div>
// );