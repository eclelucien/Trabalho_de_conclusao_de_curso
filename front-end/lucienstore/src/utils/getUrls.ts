
const Urls: any = {
    dev: {
        NUVANN_API: 'https://lucienstore.azurewebsites.net/api/v1'
    },
    prod: {
        NUVANN_API: 'https://lucienstore.azurewebsites.net/api/v1'
    }
}

const ENV: string = 'dev'
export default Urls[ENV];

