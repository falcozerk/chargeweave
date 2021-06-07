import {API_BASE_URL,ACCESS_TOKEN} from "../../../../websave/src/util/Constants";

import {doFetch} from "../Common";

//--------

export function login(loginRequest) {
    return doFetch({
        url: API_BASE_URL + "/auth/login",
        method: 'POST',
        body: JSON.stringify(loginRequest)
    });
}

//--------

export function signup(signupRequest) {
    return doFetch({
        url: API_BASE_URL + "/auth/signup",
        method: 'POST',
        body: JSON.stringify(signupRequest)
    });
}

//--------

export function checkUsernameAvailability(username) {
    return doFetch({
        url: API_BASE_URL + "/user/checkUsernameAvailability?username=" + username,
        method: 'GET'
    });
}

//--------

export function checkEmailAvailability(email) {
    return doFetch({
        url: API_BASE_URL + "/user/checkEmailAvailability?email=" + email,
        method: 'GET'
    });
}

//--------

export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return doFetch({
        url: API_BASE_URL + "/user/me",
        method: 'GET'
    });
}

//--------

export function getUserProfile(username) {
    return doFetch({
        url: API_BASE_URL + "/users/" + username,
        method: 'GET'
    });

//--------

}

