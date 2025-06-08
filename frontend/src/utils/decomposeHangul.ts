export function decomposeHangul(text: string): string[] {
    const result: string[] = [];

    for (const char of text) {
        const code = char.charCodeAt(0);

        if (code >= 0xAC00 && code <= 0xD7A3) {
            const CHOSUNG = [
                "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ",
                "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ",
            ];
            const JUNGSUNG = [
                "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ",
                "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ",
            ];
            const JONGSUNG = [
                "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ",
                "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ",
                "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ",
            ];

            const baseCode = code - 0xAC00;
            const chosungIndex = Math.floor(baseCode / (21 * 28));
            const jungsungIndex = Math.floor((baseCode % (21 * 28)) / 28);
            const jongsungIndex = baseCode % 28;

            result.push(CHOSUNG[chosungIndex]);
            result.push(JUNGSUNG[jungsungIndex]);
            if (JONGSUNG[jongsungIndex] !== "") {
                result.push(JONGSUNG[jongsungIndex]);
            }
        } else {
            result.push(char);
        }
    }

    return result;
}
