import { Schema } from "redux-orm";
import { Ability, Player } from "./models";

const schema = new Schema();

schema.register(Ability, Player);

export default schema;