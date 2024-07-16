// Bài 1:
function calculateFactorial(n) {
    if (n == 0 || n == 1) {
        return 1;
    } else {
        let number = 1;
        for (let i = 2; i <= n; i++) {
            number *= i;
        }
        return number;
    }
}
console.log(calculateFactorial(5));

// Bài 2:
function reverseString(str) {
    let reversed = "";
    for (let i = str.length - 1; i >= 0; i--) {
        reversed += str[i];
    }
    return reversed;
}
console.log(reverseString("hello"));

// Bài 3:
function translate(countryCode) {
    switch (countryCode) {
        case 'VN':
            return 'Xin chào';
        case 'EN':
            return 'Hello';
        case 'GER':
            return 'Hallo'
        case 'FR':
            return 'Bonjour'
    }
}
console.log(translate('GER'));
console.log(translate('FR'));

// Bài 4:
function subString(str) {
    if (str.length > 15) {
        return str.substring(0, 10) + '...';
    } else {
        return str;
    }
}
console.log(subString('xinchaocacbandenvoiTechmaster'));
