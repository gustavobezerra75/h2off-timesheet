const API = "http://localhost:8080/api/v1";

export function get(url, token, params) {
    const qs = [];
    if (params) {
        for (let param in params) {
            if (params[param] != null) {
                const p = encodeURIComponent(param);
                let v;
                if (Object.prototype.toString.call(params[param]) === '[object Array]') {
                    v = encodeURIComponent(params[param].join(','));
                } else {
                    v = encodeURIComponent(params[param]);
                }

                qs.push(p + '=' + v);
            }
        }
    }

    let query = '';
    if (qs.length > 0) {
        query = '?' + qs.join('&');
    }

    return fetchJSON('get', url + query, token);

}

export function post(url, token, data) {
    return fetchJSON('post', url, token, data);
}

export function put(url, token, data) {
    return fetchJSON('put', url, token, data);
}

function fetchJSON(method, url, token, data) {

    return new Promise((resolve, reject) => {

        if (url.match(/^\//)) {
            url = API + url;
        }

        let headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        };

        if (token) {
            headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token,
            };
        }

        let requestData = {method: method, headers: headers};

        let body = null;
        if (method !== 'get' && data != null) {

            if (data.isformdata) {
                body = data;
                delete headers['Content-Type'] //'multipart/form-data'
            } else {
                body = JSON.stringify(data);
                //console.log('post data', body)
            }

            requestData.body = body;

        }

        let request = new Request(url, requestData);

        fetch(request).then(response => {
            //console.log('raw response:', response);
            return response.json().then(json => {
                return response.ok ? resolve(json) : reject(json)
            }).catch((err) => {
                console.log('error: ', err);
            })
        }).catch((err) => {
            console.log('error here', err);
        });

    });

}