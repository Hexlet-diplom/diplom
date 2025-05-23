const particles = () => {
    const symbols = ['∑', 'π', '≠', '∞', '{}', '</>', '∫', 'Δ', 'λ', '≈', 'α', 'β', '⚙', '★', '✦', '⊕',
        '○', '●', '▲', '▼', '◆', '■', '◯', '△', '⬤', '⬛'];
    const count = 100;
    for (let i = 0; i < count; i++) {
        const particle = document.createElement('div');
        particle.className = 'particle';
        particle.textContent = symbols[Math.floor(Math.random() * symbols.length)];
        particle.style.left = `${Math.random() * 100}vw`;
        particle.style.top = `${Math.random() * 100 + 100}vh`;
        particle.style.fontSize = `${Math.random() * 2 + 0.8}rem`;
        particle.style.animationDelay = `${Math.random() * 10}s`;
        particle.style.animationDuration = `${15 + Math.random() * 15}s`;
        particle.style.opacity = `${Math.random() * 0.3 + 0.05}`;
        document.body.appendChild(particle);
    }
};

particles();
