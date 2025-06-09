// src/types/musicPost.ts
export interface MusicPost {
    id: number;
    text: string;
    videoUrl: string;
    imageUrl: string;
    author: string;
    createdDate: string;
    updatedDate: string;
    rating: number;
}

export interface MusicPostRes {
    id: number;
    text: string;
    videoUrl: string;
    imageUrl: string;
    author: string;
    createdDate: string;
    updatedDate: string;
    rating: number;
}

export interface MusicPostCreateReq {
    text: string;
    videoUrl: string;
    imageUrl: string;
    author: string;
    rating: number;
}

export interface MusicPostUpdateReq {
    id: number;
    text: string;
    videoUrl: string;
    imageUrl: string;
    author: string;
    rating: number;
}