export function createBlurredCoverElement(elementToCover) {
    if (!(elementToCover instanceof Element)) {
        throw new Error('Invalid element provided. Please provide a valid DOM element.');
    }

    const coverElement = document.createElement('div');
    coverElement.classList.add('cover');
    coverElement.style.position = 'absolute';

    const rect = elementToCover.getBoundingClientRect();
    coverElement.style.width = `${rect.width}px`;
    coverElement.style.height = `${rect.height}px`;
    coverElement.style.top = `${rect.top + window.pageYOffset}px`;
    coverElement.style.left = `${rect.left + window.pageXOffset}px`;

    return coverElement;
}
