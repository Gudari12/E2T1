import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ZitakPage } from './zitak.page';

describe('ZitakPage', () => {
  let component: ZitakPage;
  let fixture: ComponentFixture<ZitakPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(ZitakPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
