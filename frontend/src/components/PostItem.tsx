import React, { useState } from 'react';
import type { MusicPost } from '@utils/types/musicPost';
import './PostItem.css';
import { updateMusicPost } from '@features/music/musicPostApi.ts';


export const PostItem = ({
    post, align, onUpdatePost
}: {
    post: MusicPost;
    align: 'left' | 'right';
    onUpdatePost: (updated: MusicPost) => void;
}) => {
    const isLeft = align === 'left';
    const [editMode, setEditMode] = useState(false); // 수정 가능 상태 관리
    const [editedText, setEditedText] = useState(post.text); // 수정 텍스트 관리, 최초엔 기존 텍스트로.


    const handleSave = async () => {
        try {
            const updated = await updateMusicPost(post.id, {
                ...post,
                text: editedText,
            });
            onUpdatePost(updated);
            setEditMode(false);
        } catch (error) {
            console.error('Fail Update', error);
            alert("Fail...");
        }
    };

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

                {/* 텍스트 수정하기*/}
                {editMode ? (
                    <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
                        <input
                            value={editedText}
                            onChange={(e) => setEditedText(e.target.value)}
                        />
                        <div>
                            <button onClick={handleSave}> Save</button>
                            <button onClick={() => setEditMode(false)}> X </button>
                        </div>
                    </div>
                ) : (
                    <>
                        <div className="post-item-text">{post.text}</div>
                        <button onClick={() => setEditMode(true)}> Edit </button>
                    </>
                )};
            </div>
        </div >
    );
};
