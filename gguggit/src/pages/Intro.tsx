import React, { useEffect, useState, useRef } from "react";

export const Intro = () => {
    const [text, setText] = useState("");
    const hasStarted = useRef(false);
    const indexRef = useRef(0); // index 를 ref 로 안전하게 관리

    useEffect(() => {
        console.log(">>> updated text: " + text);
    }, [text]);

    useEffect(() => {
        if (hasStarted.current) return;
        hasStarted.current = true;

        const targetText = "안녕.";
        const targetTextArray = targetText.split('');
        console.log(">>> " + targetTextArray);

        const typeLetter = () => {
            const currentIndex = indexRef.current;

            if (currentIndex < targetTextArray.length) {
                setText((prev) => {
                    const newText = prev + targetTextArray[currentIndex];
                    console.log(">>> text (in setText): " + newText);
                    return newText;
                });

                indexRef.current = currentIndex + 1; // index 안전하게 업데이트
                console.log(">>> index: " + indexRef.current);

                setTimeout(typeLetter, 400);
            } else {
                setTimeout(() => {
                    console.log(">>> next screen");
                }, 1000);
            }
        };

        typeLetter();

    }, []);

    return (
        <div
            style={{
                backgroundColor: "black",
                color: "white",
                height: "100vh",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                fontSize: "3rem",
                fontFamily: "sans-serif",
            }}
        >
            {text}
        </div>
    );
};
