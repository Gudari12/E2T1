import { TestBed } from '@angular/core/testing';

import { Apizitak } from './apizitak';

describe('Apizitak', () => {
  let service: Apizitak;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Apizitak);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
