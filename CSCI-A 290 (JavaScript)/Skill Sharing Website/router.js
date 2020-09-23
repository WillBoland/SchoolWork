const {parse} = require("url");

//Creates a class Router that allows us to handle requests
module.exports = class Router {
  constructor() {
    this.routes = [];
  }
  
  /*
   * Adds a request to the router.
   */
  add(method, url, handler) {
    this.routes.push({method, url, handler});
  }
  
  /*
   * Resolves the requests given a context and request.
   * Returns null of no request was found.
   */
  resolve(context, request) {
    let path = parse(request.url).pathname
    for(let {method, url, hanlder} of this.routes) {
      let match = url.exec(path);
      if(!match || request.method != method)
        continue;
      let urlParts = match.slice(1).map(decodeURIComponent);
      return handler(context, ...urlParts, request);
    }
    return null
  }
}
