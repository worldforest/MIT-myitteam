export default {
    isLoggedIn(state) {
        if (state.authToken) {
            state.isLoggedIn = true
            return state.isLoggedIn
        }
        else {
            state.isLoggedIn = false
            return state.isLoggedIn
        }
    },
    isEmail(state) {
        return state.email 
    }
}

//