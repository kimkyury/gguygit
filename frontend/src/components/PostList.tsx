import type { MusicPost } from '@utils/types/musicPost'
import { PostItem } from '@components/PostItem';

interface Props {
    posts: MusicPost[];
    onUpdatePost: (updated: MusicPost) => void;
    onDeletePost: (id: number) => void;
}

export const PostList = ({
    posts
    , onUpdatePost
    , onDeletePost
}: Props) => {

    console.log(posts.map((post, index) => `index ${index}: post.id = ${post.id}`));

    return (
        <div style={{ marginTop: '60px' }}>
            {posts.map((post) => (
                <PostItem onUpdatePost={onUpdatePost} key={post.id} post={post} onDeletePost={onDeletePost} />
            ))}
        </div>
    );
};
