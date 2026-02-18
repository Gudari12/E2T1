import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ZerbitzuakPage } from './zerbitzuak.page';

describe('ZerbitzuakPage', () => {
  let component: ZerbitzuakPage;
  let fixture: ComponentFixture<ZerbitzuakPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(ZerbitzuakPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
