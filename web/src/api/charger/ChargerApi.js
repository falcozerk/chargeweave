import {API_BASE_URL, CHARGER_LIST_SIZE} from "../../../../websave/src/util/Constants";

import {doFetch} from "../Common";

//--------

export function getAllChargers(page, size) {
    page = page || 0;
    size = size || CHARGER_LIST_SIZE;

    return doFetch({
        url: API_BASE_URL + "/chargers?page=" + page + "&size=" + size,
        method: 'GET'
    });
}

//--------

export function createCharger(chargerData) {
    return doFetch({
        url: API_BASE_URL + "/chargers",
        method: 'POST',
        body: JSON.stringify(chargerData)
    });
}

//--------

export function castVisit(visitData) {
    return doFetch({
        url: API_BASE_URL + "/chargers/" + visitData.chargerId + "/visits",
        method: 'POST',
        body: JSON.stringify(visitData)
    });
}

//--------

export function getUserCreatedChargers(username, page, size) {
    page = page || 0;
    size = size || CHARGER_LIST_SIZE;

    return doFetch({
        url: API_BASE_URL + "/users/" + username + "/chargers?page=" + page + "&size=" + size,
        method: 'GET'
    });
}

//--------

export function getUserVisitedChargers(username, page, size) {
    page = page || 0;
    size = size || CHARGER_LIST_SIZE;

    return doFetch({
        url: API_BASE_URL + "/users/" + username + "/visits?page=" + page + "&size=" + size,
        method: 'GET'
    });

//--------

}
