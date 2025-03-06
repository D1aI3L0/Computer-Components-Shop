import { TestBed } from '@angular/core/testing';

import { InputHelperService } from './input-helper.service';

describe('InputHelperService', () => {
  let service: InputHelperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InputHelperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
