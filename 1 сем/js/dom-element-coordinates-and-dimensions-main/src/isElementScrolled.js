export function isElementScrolled(element) {
    if (element === null || element === undefined) {
        return {
            scrollTop: null,
            scrollLeft: null,
            isScrolled: null
        };
    }

    const scrollTop = element.scrollTop || document.documentElement.scrollTop || document.body.scrollTop || 0;
    const scrollLeft = element.scrollLeft || document.documentElement.scrollLeft || document.body.scrollLeft || 0;

    const isScrolled = (scrollTop > 0 || scrollLeft > 0);

    return {
        scrollTop,
        scrollLeft,
        isScrolled
    };
}
І