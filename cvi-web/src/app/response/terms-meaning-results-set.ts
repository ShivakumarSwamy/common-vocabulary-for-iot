import { SearchItemDetails } from './search-item-details';

export interface TermsMeaningResultsSet {
  exactResults: Array<SearchItemDetails>;
  relatedResults: Array<SearchItemDetails>;
}
