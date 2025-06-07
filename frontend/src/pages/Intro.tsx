import React, { useEffect, useState, useRef } from "react";
import { useNavigate } from 'react-router-dom';
import Hangul from 'hangul-js'

import { decomposeHangul } from "@utils/decomposeHangul";
import "./Intro.css";

export const Intro = () => {
    const [typedChars, setTypedChars] = useState<string[]>([]);
    const [displayText, setDisplayText] = useState<string>("");

    const hasStarted = useRef(false);
    const indexRef = useRef(0);

    /* Home으로 이동시키기 위함*/
    const navigate = useNavigate();

    /* TEXT 확인용 */
    useEffect(() => {
        console.log(">>> updated text: " + displayText);
    }, [displayText]);

    useEffect(() => {
        if (hasStarted.current) return;
        hasStarted.current = true;

        const targetText = "안녕.";
        const targetTextArray = decomposeHangul(targetText);
        console.log(">>> " + targetTextArray);

        const typeLetter = () => {
            const currentIndex = indexRef.current;

            if (currentIndex < targetTextArray.length) {

                setTypedChars((prev) => {
                    // 한글에 대한 초성배열 생성과 음절 재조합
                    const newTyped = [...prev, targetTextArray[currentIndex]];
                    const composed = Hangul.assemble(newTyped);
                    setDisplayText(composed);

                    return newTyped;
                });

                indexRef.current = currentIndex + 1; // index 안전하게 업데이트
                setTimeout(typeLetter, 200);

            } else {
                setTimeout(() => {
                    console.log(">>> next screen → navigate to /home");
                    navigate('/music'); // ⭐ 자동 이동!!
                }, 1000);
            }
        };

        typeLetter();

    }, []);

    return (
        <div className="intro-container">
            <div className="intro-text">
                {displayText}
                <span className="blinking-cursor"></span>
            </div>
        </div>
    );
};
