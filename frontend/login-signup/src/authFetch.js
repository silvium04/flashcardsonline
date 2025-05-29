export const authFetch = async (url, options = {}) => {
    const token = localStorage.getItem("token");

    const headers = {
        ...(options.headers || {}),
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`,
    };

    const finalOptions = {
        ...options,
        headers,
    };

    return fetch(url, finalOptions);
};