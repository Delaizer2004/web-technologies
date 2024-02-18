export function isElementVisible(element) {
    if (element === null || element === undefined) {
        return false;
    }

    if (!(element instanceof Element)) {
        throw new Error('Invalid element provided. Please provide a valid DOM element.');
    }

    const style = window.getComputedStyle(element);
    return style.getPropertyValue('display') !== 'none' &&
           style.getPropertyValue('visibility') !== 'hidden' &&
           element.offsetWidth > 0 &&
           element.offsetHeight > 0;
}
