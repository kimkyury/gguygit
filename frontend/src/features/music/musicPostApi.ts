import type { MusicPostCreateReq, MusicPostUpdateReq, MusicPostRes } from '@utils/types/musicPost'
import { axiosInstance } from '@/utils/axiosInstance';
const API_URL = '/api/music-posts';

export const createMusicPost = async (req: MusicPostCreateReq): Promise<MusicPostRes> => {
    const response = await axiosInstance.post<MusicPostRes>(API_URL, req);
    return response.data;
};

export const updateMusicPost = async (id: number, req: MusicPostUpdateReq): Promise<MusicPostRes> => {
    const response = await axiosInstance.put<MusicPostRes>(`${API_URL}\\${id}`, req);
    return response.data;
}

export const getAllMusicPosts = async (): Promise<MusicPostRes[]> => {
    const response = await axiosInstance.get<MusicPostRes[]>(API_URL);
    return response.data;
};