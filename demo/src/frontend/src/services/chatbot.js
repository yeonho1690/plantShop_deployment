import http from "../http-common";
import axios from 'axios';
const API_URL = 'http://115.85.180.27:8090/api/chatbot/chatbot';
class chatbotService {
    getchatbot() {
        return http.get(`/chatbot/chatbot`);
        }
    getchatbot2(){
        return axios
        .get(API_URL)
        .then(response =>{
            return response.data;
        })
    }

    }
    export default new chatbotService();