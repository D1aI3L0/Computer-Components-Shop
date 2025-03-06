import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphicCardListComponent } from './graphic-card-list.component';

describe('GraphicCardListComponent', () => {
  let component: GraphicCardListComponent;
  let fixture: ComponentFixture<GraphicCardListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GraphicCardListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GraphicCardListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
