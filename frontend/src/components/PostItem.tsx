import { useState } from 'react';
import type { MusicPost } from '@utils/types/musicPost';
import './PostItem.css';
import { updateMusicPost, deleteMusicPost } from '@features/music/musicPostApi.ts';
import { FaStar, FaRegStar } from 'react-icons/fa';

interface Props {
    post: MusicPost;
    onUpdatePost: (updated: MusicPost) => void;
    onDeletePost: (id: number) => void;
}

export const PostItem = ({
    post,
    onUpdatePost,
    onDeletePost
}: Props) => {
    const [editMode, setEditMode] = useState(false);
    const [editedText, setEditedText] = useState(post.text);

    const formattedDateTime = new Date(post.createdDate).toLocaleString('ko-KR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        hour12: false,
    });

    const renderStars = (rating: number) =>
        Array.from({ length: 5 }, (_, i) =>
            i < rating ? (
                <FaStar key={i} color="#f6c945" size={18} />
            ) : (
                <FaRegStar key={i} color="#555" size={18} />
            )
        );

    const handleDelete = async () => {
        if (window.confirm(`It will be deleted. Proceed?`)) {
            await deleteMusicPost(post.id);
            onDeletePost(post.id);
        }
    };

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
            alert("Update failed...");
        }
    };

    return (
        <div className={`post-item-container`}>
            <img src={post.imageUrl} className="post-item-image" alt="Thumbnail" />

            <div className="post-item-content">
                <div className="post-item-header">
                    <div className="post-rating">{renderStars(post.rating)}</div>
                    <div className="post-item-meta">
                        <span>{post.author || 'ANONYMOUS'}</span> · <span>{formattedDateTime}</span>
                    </div>
                </div>

                {editMode ? (
                    <div className="post-edit-wrapper">
                        <textarea
                            className="post-edit-input"
                            value={editedText}
                            onChange={(e) => setEditedText(e.target.value)}
                        />
                        <div className="post-actions always-visible">
                            <button className="save" onClick={handleSave}>Save</button>
                            <button className="cancel" onClick={() => setEditMode(false)}>Cancel</button>
                        </div>
                    </div>
                ) : (
                    <>
                        <div className="post-item-text">{post.text}</div>
                        <div className="post-actions hover-only">
                            <button onClick={() => setEditMode(true)}>Edit</button>
                            <button onClick={handleDelete}>Del</button>
                        </div>
                    </>
                )}
            </div>
        </div>
    );
};
