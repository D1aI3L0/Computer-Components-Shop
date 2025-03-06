import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphicCardFormComponent } from './graphic-card-form.component';

describe('GraphicCardFormComponent', () => {
  let component: GraphicCardFormComponent;
  let fixture: ComponentFixture<GraphicCardFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GraphicCardFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GraphicCardFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
