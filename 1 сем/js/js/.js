const рядок = "Ваш_рядок_для_розділення";
const середина = Math.ceil(рядок.length / 2); // Знаходимо середину рядка

const перша_частина = рядок.slice(0, середина); // Перша половина рядка
const друга_частина = рядок.slice(середина);     // Друга половина рядка

console.log(перша_частина);
console.log(друга_частина);