export function getPageData() {
    const windowHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight || 0;
    const windowWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth || 0;

    const documentHeight = Math.max(
        document.body.scrollHeight || 0,
        document.documentElement.scrollHeight || 0,
        document.body.offsetHeight || 0,
        document.documentElement.offsetHeight || 0,
        document.body.clientHeight || 0,
        document.documentElement.clientHeight || 0
    );

    const documentWidth = Math.max(
        document.body.scrollWidth || 0,
        document.documentElement.scrollWidth || 0,
        document.body.offsetWidth || 0,
        document.documentElement.offsetWidth || 0,
        document.body.clientWidth || 0,
        document.documentElement.clientWidth || 0
    );

    const currentScrollFromTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    const currentScrollFromLeft = window.pageXOffset || document.documentElement.scrollLeft || document.body.scrollLeft || 0;

    return {
        windowHeight,
        windowWidth,
        documentHeight,
        documentWidth,
        currentScrollFromTop,
        currentScrollFromLeft
    };
}
