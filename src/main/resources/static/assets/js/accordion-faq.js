document.addEventListener('DOMContentLoaded', function() {
    const faqQuestions = document.querySelectorAll('.faq__question');

    faqQuestions.forEach(question => {
        question.addEventListener('click', () => {
            document.querySelectorAll('.faq__answer').forEach(answer => {
                answer.classList.remove('active');
            });

            document.querySelectorAll('.faq__question').forEach(q => {
                q.classList.remove('active');
            });

            const answer = question.nextElementSibling;
            answer.classList.add('active');
            question.classList.add('active');
        });
    });

    if (faqQuestions.length > 0) {
        faqQuestions[0].classList.add('active');
        faqQuestions[0].nextElementSibling.classList.add('active');
    }
});