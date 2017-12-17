import { Fetcher } from 'api/fetch';
import { validationEndpoint, validationAffinitiesEndpoint } from 'builder/requests/endpoints';

export const teamValidationFetcher = new Fetcher(validationEndpoint);
