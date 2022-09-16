import axios from "axios";
export default axios.create({
  baseURL: "http://115.85.180.27:8090/api",
  headers: {
    "Content-type": "application/json"
  }
});