package ir.gooble.clinic.oracle;

public enum Api {

    BONUS(Method.POST) {
        @Override
        public String toString() {
            return "/ads/validate";
        }
    },

    REGISTER(Method.POST) {
        @Override
        public String toString() {
            return "/user/signup";
        }
    },

    LOGIN(Method.POST) {
        @Override
        public String toString() {
            return "/user/login";
        }
    },

    VERIFY(Method.POST) {
        @Override
        public String toString() {
            return "/user/login-verify";
        }
    },

    INFO(Method.GET) {
        @Override
        public String toString() {
            return "/user/info";
        }
    },

    PUSH_TOKEN(Method.POST) {
        @Override
        public String toString() {
            return "/user/get-notif-token";
        }
    },

    PROFILE(Method.GET) {
        @Override
        public String toString() {
            return "/user/profile?id={}";
        }
    },

    SLOTS(Method.GET) {
        @Override
        public String toString() {
            return "/user/slots";
        }
    },

    HISTORY(Method.GET) {
        @Override
        public String toString() {
            return "/user/match-history";
        }
    },

    EDIT(Method.POST) {
        @Override
        public String toString() {
            return "/user/edit";
        }
    },

    LEAGUE_BOARD(Method.GET) {
        @Override
        public String toString() {
            return "/user/league-leaderboard";
        }
    },

    LEAGUE_BOARD_UPDATED(Method.GET) {
        @Override
        public String toString() {
            return "/user/leagues-overview";
        }
    },

    TOTAL_BOARD(Method.GET) {
        @Override
        public String toString() {
            return "/user/leaderboard";
        }
    },

    AVATARS(Method.GET) {
        @Override
        public String toString() {
            return "/avatars";
        }
    },

    AVATAR_IMAGE(Method.GET) {
        @Override
        public String toString() {
            return "/avatars/get?id={}";
        }
    },

    AVATAR_PURCHASE(Method.POST) {
        @Override
        public String toString() {
            return "/avatars/purchase";
        }
    },

    AVATAR_SELECT(Method.POST) {
        @Override
        public String toString() {
            return "/avatars/select";
        }
    },

    CATEGORIES(Method.GET) {
        @Override
        public String toString() {
            return "/question/cats";
        }
    },

    QUESTION(Method.POST) {
        @Override
        public String toString() {
            return "/question/add";
        }
    },

    MARKET(Method.GET) {
        @Override
        public String toString() {
            return "/market";
        }
    },

    MATCH(Method.GET) {
        @Override
        public String toString() {
            return "/match?id={}";
        }
    },

    PICK_CATEGORY(Method.POST) {
        @Override
        public String toString() {
            return "/match/round/pick-cat";
        }
    },

    GET_QUESTION(Method.GET) {
        @Override
        public String toString() {
            return "/match/question?id={}";
        }
    },

    SURRENDER(Method.POST) {
        @Override
        public String toString() {
            return "/match/surrender";
        }
    },

    RANDOM_CATEGORY(Method.GET) {
        @Override
        public String toString() {
            return "/match/round/rand-cats?match_id={}";
        }
    },

    ANSWER(Method.POST) {
        @Override
        public String toString() {
            return "/match/question/answer";
        }
    },

    HANDSHAKE(Method.POST) {
        @Override
        public String toString() {
            return "/handshake";
        }
    },

    CHAT(Method.POST) {
        @Override
        public String toString() {
            return "/match/send-message";
        }
    },

    SHOW_QUESTION(Method.POST) {
        @Override
        public String toString() {
            return "/match/question/display";
        }
    },

    USE_BOOST(Method.POST) {
        @Override
        public String toString() {
            return "/match/question/consume-booster";
        }
    },

    PURCHASE_MARKET(Method.POST) {
        @Override
        public String toString() {
            return "/market/validate-purch";
        }
    },

    PURCHASE_SLOT_PRICE(Method.POST) {
        @Override
        public String toString() {
            return "/user/slots/purchase-verify";
        }
    },

    PURCHASE_SLOT_COIN(Method.POST) {
        @Override
        public String toString() {
            return "/user/slots/coin-purchase";
        }
    },

    NEWS(Method.GET) {
        @Override
        public String toString() {
            return "/news";
        }
    },

    MAKE_FRIEND(Method.POST) {
        @Override
        public String toString() {
            return "/user/make-friend";
        }
    };


    public static final String ROUTE = "q.ramagegames.com:443";
    private int id;

    Api(int id) {
        this.id = id;
    }

    public int getMethod() {
        return id;
    }
}
