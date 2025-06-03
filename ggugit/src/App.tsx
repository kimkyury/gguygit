import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { Intro } from '@pages/Intro';
import { Home } from '@pages/Home';
import Music from '@pages/Music';
import './App.css'

export default function App() {
    return (
        <Routes>
            <Route path="/" element={<Intro />} />
            <Route path="/home" element={<Home />} />
            <Route path="/music" element={<Music />} />
        </Routes>
    );
}
